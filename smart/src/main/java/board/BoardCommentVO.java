package board;

import java.sql.Date;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class BoardCommentVO {
	private int id, board_id;
	private String content, writer, name;
	private Date writedate;
}
