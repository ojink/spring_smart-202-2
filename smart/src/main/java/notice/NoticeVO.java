package notice;

import java.sql.Date;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class NoticeVO {
	private int id, readcnt;
	private String title, content, writer, name;
	private Date writedate;
}
