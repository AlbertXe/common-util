def map=[:]
map."an with space"="ALLOWED"
map.'with-squotes'="ALLOWED"

assert map."an with space"=="ALLOWED"