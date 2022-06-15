package chat.controller;

import java.io.IOException;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.websocket.Session;

import com.google.gson.Gson;

import chat.ws.HelloWebSocket;

/**
 * Servlet implementation class ChatClientsServlet
 */
@WebServlet("/chat/clients")
public class ChatClientsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 1. 업무로직 : @ServerEndpoint clients
		Map<String, Session> clients = HelloWebSocket.clients;
		Set<String> memberIdSet = clients.keySet();
		System.out.println("접속자 목록 : " + memberIdSet);
		
		// 2. 응답 작성 : json
		response.setContentType("application/json; charset=utf-8");
		new Gson().toJson(memberIdSet, response.getWriter());
	}

}
