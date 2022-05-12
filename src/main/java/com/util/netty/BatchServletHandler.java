package com.util.netty;

/**
 * @description:
 * @author: AlbertXe
 * @create: 2022-05-11 22:27
 */
public class BatchServletHandler extends AbstractSocketDataHandler {
    private static final String TOKEN_KEY = "_t_";
    private Facade facade;
    private PkgParser parser;
    private PkgWrapper wrapper;
    private SocketPkgHeaderHandler pkgHeaderHandler;

    protected String pkgEncoding;


    @Override
    public void init(String encoding, String pkgType) {
        this.pkgEncoding = encoding;
        parser = new PkgParserProxy(new JsonParser());

    }

    @Override
    public String handle(String v1) {
        return null;
    }

    @Override
    public void destroy() {

    }

    @Override
    public String processException(Throwable throwable) {
        return null;
    }
}
