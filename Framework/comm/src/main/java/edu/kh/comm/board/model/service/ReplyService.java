package edu.kh.comm.board.model.service;

import java.util.List;

import edu.kh.comm.board.model.vo.Reply;

public interface ReplyService {

	/** ´ñ±Û ¸ñ·Ï Á¶È¸ Service
	 * @param boardNo
	 * @return rList
	 */
	List<Reply> selectReplyList(int boardNo);

	/** ´ñ±Û µî·Ï Service
	 * @param reply
	 * @return result
	 */
	int insertReply(Reply reply);

	/** ´ñ±Û »èÁ¦ Service
	 * @param replyNo
	 * @return result
	 */
	int deleteReply(int replyNo);

	/** ´ñ±Û ¼öÁ¤ Service
	 * @param reply
	 * @return result
	 */
	int updateReply(Reply reply);

}
