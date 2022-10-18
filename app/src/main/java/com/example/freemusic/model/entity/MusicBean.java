package com.example.freemusic.model.entity;

public class MusicBean {

    private String name;
    private String author;
    private long size;
    private int duration;
    private String path;
    private long id;
    private long albumId;
    private String album;

    public String getAlbum() {
        return album;
    }

    public void setAlbum(String album) {
        this.album = album;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public long getSize() {
        return size;
    }

    public void setSize(long size) {
        this.size = size;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getAlbumId() {
        return albumId;
    }

    public void setAlbumId(long albumId) {
        this.albumId = albumId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MusicBean)) return false;

        MusicBean musicBean = (MusicBean) o;

        if (getSize() != musicBean.getSize()) return false;
        if (getDuration() != musicBean.getDuration()) return false;
        if (getId() != musicBean.getId()) return false;
        if (getAlbumId() != musicBean.getAlbumId()) return false;
        if (getName() != null ? !getName().equals(musicBean.getName()) : musicBean.getName() != null)
            return false;
        if (getAuthor() != null ? !getAuthor().equals(musicBean.getAuthor()) : musicBean.getAuthor() != null)
            return false;
        if (getPath() != null ? !getPath().equals(musicBean.getPath()) : musicBean.getPath() != null)
            return false;
        return getAlbum() != null ? getAlbum().equals(musicBean.getAlbum()) : musicBean.getAlbum() == null;
    }

    @Override
    public int hashCode() {
        int result = getName() != null ? getName().hashCode() : 0;
        result = 31 * result + (getAuthor() != null ? getAuthor().hashCode() : 0);
        result = 31 * result + (int) (getSize() ^ (getSize() >>> 32));
        result = 31 * result + getDuration();
        result = 31 * result + (getPath() != null ? getPath().hashCode() : 0);
        result = 31 * result + (int) (getId() ^ (getId() >>> 32));
        result = 31 * result + (int) (getAlbumId() ^ (getAlbumId() >>> 32));
        result = 31 * result + (getAlbum() != null ? getAlbum().hashCode() : 0);
        return result;
    }
}
