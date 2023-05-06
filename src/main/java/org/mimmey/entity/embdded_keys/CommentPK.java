package org.mimmey.entity.embdded_keys;

import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import org.mimmey.entity.Track;
import org.mimmey.entity.User;

import java.io.Serializable;

@Data
public class CommentPK implements Serializable {

    @ManyToOne
    @JoinColumn(name = "comment_author_id")
    private User commentAuthor;

    @ManyToOne
    @JoinColumn(name = "track_id")
    private Track track;
}
