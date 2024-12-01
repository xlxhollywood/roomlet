package com.example.board;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class BoardDAO {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    private final String BOARD_INSERT = "insert into BOARD (title, writer, content) values (?,?,?)";
    private final String BOARD_UPDATE = "update BOARD set title=?, writer=?, content=? where seq=?";
    private final String BOARD_DELETE = "delete from BOARD  where seq=?";
    private final String BOARD_GET = "select * from BOARD  where seq=?";
    private final String BOARD_LIST = "select * from BOARD order by seq desc";

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public int insertBoard(BoardVO vo) {
        return jdbcTemplate.update(BOARD_INSERT, new Object[]{vo.getTitle(), vo.getWriter(), vo.getContent()});
    }

    // 글 삭제
    public int deleteBoard(int id) {
        return jdbcTemplate.update(BOARD_DELETE, id);
    }

    public int updateBoard(BoardVO vo) {
        return jdbcTemplate.update(BOARD_UPDATE, new Object[]{vo.getTitle(), vo.getWriter(), vo.getContent(), vo.getSeq()});
    }

    public BoardVO getBoard(int seq) {
        return jdbcTemplate.queryForObject(BOARD_GET, new Object[]{seq},
                new BeanPropertyRowMapper<BoardVO>(BoardVO.class));
    }

    public int getTotalcnt() {
        return (int)jdbcTemplate.queryForObject("Select count(*) from BOARD",Integer.class );
    }

    public List<BoardVO> getBoardList() {
        return jdbcTemplate.query(BOARD_LIST, new RowMapper<BoardVO>() {
            public BoardVO mapRow(ResultSet rs, int rowNum) throws SQLException {
                BoardVO one = new BoardVO();
                one.setSeq(rs.getInt("seq"));
                one.setTitle(rs.getString("title"));
                one.setContent(rs.getString("content"));
                one.setRegdate(rs.getDate("regdate"));
                one.setWriter(rs.getString("writer"));
                return one;
            }
        });
    }
}
