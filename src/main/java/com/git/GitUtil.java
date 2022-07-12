package com.git;

import com.jcraft.jsch.Session;
import lombok.SneakyThrows;
import org.eclipse.jgit.api.CloneCommand;
import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.ListBranchCommand;
import org.eclipse.jgit.api.TransportConfigCallback;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.lib.Ref;
import org.eclipse.jgit.transport.JschConfigSessionFactory;
import org.eclipse.jgit.transport.OpenSshConfig;
import org.eclipse.jgit.transport.SshSessionFactory;
import org.eclipse.jgit.transport.SshTransport;
import org.eclipse.jgit.transport.Transport;
import org.eclipse.jgit.transport.UsernamePasswordCredentialsProvider;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * @description:
 * @author: AlbertXe
 * @create: 2022-04-29 11:21
 */
public class GitUtil {

    public static String localRepoPath = "D:/repo";
    public static String localRepoGitConfig = "D:/repo/.git";
    public static String remoteSSHURI = "git@github.com:AlbertXe/common-util.git";
    public static String remoteHttpsURI = "https://github.com/AlbertXe/common-util.git";
    public static String localCodeDir = "D:/platplat";

    static UsernamePasswordCredentialsProvider provider = new UsernamePasswordCredentialsProvider(
            "563000409@qq.com", "xhw529660972");

    /**
     * 新建一个分支并同步到远程仓库
     *
     * @param branchName
     * @throws IOException
     * @throws GitAPIException
     */
    @SneakyThrows
    public static String newBranch(String branchName) {
        String newBranchIndex = "refs/heads/" + branchName;
        Git git = Git.open(new File(localRepoGitConfig));
        List<Ref> refs = git.branchList().call();
        for (Ref ref : refs) {
            System.out.println(ref.getName());
            if (ref.getName().equals(newBranchIndex)) {
                git.branchDelete().setForce(true).setBranchNames(branchName).call();
            }
        }
        //新建分支
        Ref ref = git.branchCreate().setName(branchName).call();
        //推送到远程
        git.push().setCredentialsProvider(provider).add(ref).call();
        return remoteSSHURI + " " + "feature/" + branchName;
    }

    @SneakyThrows
    public static void commitFiles() {
        String filePath = localRepoPath+"/.testCommit.txt";
        Git git = Git.open(new File(localRepoGitConfig));
        //创建用户文件的过程
        File myfile = new File(filePath);
        myfile.createNewFile();
        git.add().addFilepattern("*").call();
        //提交
        git.commit().setMessage("Added pets").call();
        //推送到远程
        git.push().setCredentialsProvider(provider).call();
    }

    public static boolean pullBranchToLocal(String cloneURL) {
        boolean resultFlag = false;
        String[] splitURL = cloneURL.split(" ");
        String branchName = splitURL[1];
        String fileDir = localCodeDir + "/" + branchName;
        //检查目标文件夹是否存在
        File file = new File(fileDir);
        if (file.exists()) {
            deleteFolder(file);
        }
        Git git;
        try {
            git = Git.open(new File(localRepoGitConfig));
            git.cloneRepository().setURI(cloneURL).setDirectory(file).call();
            resultFlag = true;
        } catch (IOException e) {
            e.printStackTrace();
        } catch (GitAPIException e) {
            e.printStackTrace();
        }
        return resultFlag;
    }

    public static void deleteFolder(File file) {
        if (file.isFile() || file.list().length == 0) {
            file.delete();
        } else {
            File[] files = file.listFiles();
            for (int i = 0; i < files.length; i++) {
                deleteFolder(files[i]);
                files[i].delete();
            }
        }
    }

    public static void setupRepo() throws GitAPIException {
        //建立与远程仓库的联系，仅需要执行一次
        Git git = Git.cloneRepository().setURI(remoteSSHURI).setDirectory(new File(localRepoPath)).call();
    }

    @SneakyThrows
    public static boolean pull(String remoteBranchName) {
        Git git = Git.cloneRepository().setURI(remoteBranchName)
                .setDirectory(new File(localRepoPath)).setCredentialsProvider(provider).call();
        return true;
    }

    public static void ssh() throws GitAPIException {
        SshSessionFactory factory = new JschConfigSessionFactory(){
            @Override
            protected void configure(OpenSshConfig.Host hc, Session session) {
                session.setConfig("StrictHostKeyChecking","no");
            }
        };
        CloneCommand cloneCommand = Git.cloneRepository();
        cloneCommand.setURI(remoteSSHURI);
        cloneCommand.setTransportConfigCallback(new TransportConfigCallback() {
            @Override
            public void configure(Transport transport) {
                SshTransport sshTransport = ((SshTransport) transport);
                sshTransport.setSshSessionFactory(factory);
            }
        });
        Git git = cloneCommand.call();
        ListBranchCommand branchList = git.branchList();
        for (Ref ref : branchList.call()) {
            System.out.println(ref.getName());
        }
    }

    public static void lsRemoteRepository() throws GitAPIException {
        Map<String, Ref> map = Git.lsRemoteRepository().setRemote(remoteHttpsURI)
                .setCredentialsProvider(provider)
                .callAsMap();
        System.out.println(map);
    }

    public static void lsRemoteRepositoryWithSSH() throws GitAPIException {
        Map<String, Ref> map = Git.lsRemoteRepository().setRemote(remoteSSHURI)
                .setHeads(false)
                .setTransportConfigCallback(new TransportConfigCallback() {
                    @Override
                    public void configure(Transport transport) {
                        SshTransport sshTransport = ((SshTransport) transport);
                        sshTransport.setSshSessionFactory(new JschConfigSessionFactory(){
                            @Override
                            protected void configure(OpenSshConfig.Host hc, Session session) {
                                session.setConfig("StrictHostKeyChecking","no");
                            }
                        });
                    }
                })
                .callAsMap();
        System.out.println(map);
    }

    @Test
    public void pull() {
        pull("https://github.com/AlbertXe/common-util.git");
    }

    @Test
    public void testNewBranch() {
        //新建分支推送
        System.out.println(newBranch("javaGit4"));
    }
    
    @Test
    public void testCommit() {
        commitFiles();
    }

    @SneakyThrows
    @Test
    public void testSSH() {
        ssh();
    }

    @SneakyThrows
    @Test
    public void testLsRemote() {
    }
    @SneakyThrows
    @Test
    public void testLsRemoteSSH() {
        lsRemoteRepositoryWithSSH();
    }
}
