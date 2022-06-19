package chat.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.websocket.RemoteEndpoint.Basic;
import javax.websocket.Session;

import com.google.gson.Gson;

import chat.ws.HelloWebSocket;
import member.model.dto.Member;

/**
 * Servlet implementation class SendDMServlet
 */
@WebServlet("/chat/sendDM")
public class SendDMServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 1. 사용자 입력값 처리
		String dm = request.getParameter("dm");
		Map<String, Object> dmMap = new Gson().fromJson(dm, Map.class);
		System.out.println("dm = " + dm);
		System.out.println("dmMap = " + dmMap);
		
		// 1. 사용자 입력값 처리 (자신이 보낸 메세지)
		String senddm = request.getParameter("dm");
		Map<String, Object> senddmMap = new Gson().fromJson(senddm, Map.class);
		System.out.println("senddm = " + senddm);
		System.out.println("senddmMap = " + senddmMap);
		
		// 2. 업무로직 : 특정 client에게 메세지 전송
		String memberId = (String) dmMap.get("receiver");
		Session sess = HelloWebSocket.clients.get(memberId);
		String result = "";
		if(sess != null) {
			// 특정 사용자에게 메세지 전송
			Basic basic = sess.getBasicRemote();
			basic.sendText(dm);

			result = "DM을 성공적으로 전송했습니다 !";
		} else {
			result = "사용자가 더 이상 존재하지 않습니다 !";
		}
		
		// 2. 업무로직 : 본인에게 메세지 전송
		String sendmemberId = (String) senddmMap.get("sender");
		Session sendsess = HelloWebSocket.clients.get(sendmemberId);
		String sendresult = "";
		if(sess != null) {
			// 본인에게 메세지 전송
			Basic sendbasic = sendsess.getBasicRemote();
			sendbasic.sendText(senddm);

			sendresult = "DM을 성공적으로 전송했습니다 !";
		} else {
			sendresult = "사용자가 더 이상 존재하지 않습니다 !";
		}
		
		// 3. 응답처리
		response.setContentType("application/json; charset=utf-8");
		Map<String, Object> resultMap = new HashMap<>();
		resultMap.put("result", result);
		new Gson().toJson(resultMap, response.getWriter());
	}

}
