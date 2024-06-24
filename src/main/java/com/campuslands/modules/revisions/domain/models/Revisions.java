package com.campuslands.modules.revisions.domain.models;

import java.sql.Date;

/**
 * Revisions
 */
public class Revisions {

    int id;
    Date revision_date;
    int id_plane;

    public Revisions(int id, Date revision_date, int id_plane) {
        this.id = id;
        this.revision_date = revision_date;
        this.id_plane = id_plane;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getRevision_date() {
        return revision_date;
    }

    public void setRevision_date(Date revision_date) {
        this.revision_date = revision_date;
    }

    public int getId_plane() {
        return id_plane;
    }

    public void setId_plane(int id_plane) {
        this.id_plane = id_plane;
    }

}