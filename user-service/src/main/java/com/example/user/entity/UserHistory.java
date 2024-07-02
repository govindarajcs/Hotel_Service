package com.example.user.entity;

import java.io.Serializable;
import jakarta.persistence.*;
import java.util.Date;


/**
 * The persistent class for the user_history database table.
 * 
 */
@Entity
@Table(name="user_history")
@NamedQuery(name="UserHistory.findAll", query="SELECT u FROM UserHistory u")
public class UserHistory implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="USER_HISTORY_ID_GENERATOR" )
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="USER_HISTORY_ID_GENERATOR")
	private int id;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="create_timestamp")
	private Date createTimestamp;

	//bi-directional many-to-one association to UserDetail
	@ManyToOne
	@JoinColumn(name="user_id")
	private UserDetail userDetail;

	public UserHistory() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getCreateTimestamp() {
		return this.createTimestamp;
	}

	public void setCreateTimestamp(Date createTimestamp) {
		this.createTimestamp = createTimestamp;
	}

	public UserDetail getUserDetail() {
		return this.userDetail;
	}

	public void setUserDetail(UserDetail userDetail) {
		this.userDetail = userDetail;
	}

}