package club.wedcloud.www.mapper.Provider;

public class AlbumProvider {
    public String findAll(String ablumName) {

        StringBuilder build = new StringBuilder(
                "SELECT `id`,`album_name` AS albumName,`imgurl`,`status`,`created_at` AS createdAt FROM album WHERE 1=1");
        if (!"-1".equals(ablumName)) {
            build.append(" and album_name = '" + ablumName + "'");
        }
        build.append(" and `deleted_at` IS NULL ORDER BY id ASC");
        return build.toString();
    }
}
