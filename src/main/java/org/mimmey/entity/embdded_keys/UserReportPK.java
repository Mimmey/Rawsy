package org.mimmey.entity.embdded_keys;

import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import org.mimmey.entity.Report;
import org.mimmey.entity.User;

import java.io.Serializable;

@Data
public class UserReportPK implements Serializable {

    @ManyToOne
    @JoinColumn(name = "report_id")
    private Report report;

    @ManyToOne
    @JoinColumn(name = "user_subject_id")
    private User userSubject;
}
