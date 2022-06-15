package chat.ws;

import java.util.Map;

import javax.servlet.http.HttpSession;
import javax.websocket.HandshakeResponse;
import javax.websocket.server.HandshakeRequest;
import javax.websocket.server.ServerEndpointConfig;
import javax.websocket.server.ServerEndpointConfig.Configurator;

import member.model.dto.Member;

public class HelloWebSocketConfigurator extends Configurator {
	
	/**
	 * Configurator클래스는 @ServerEndpoint 클래스 설정 정보를 작성하는 클래스
	 * modifyHandshake메소드를 override해서 HttpSession객체에 접근할 수 있다.
	 */
	@Override
	public void modifyHandshake(
		ServerEndpointConfig sec, HandshakeRequest request, HandshakeResponse response) {
		
		HttpSession httpSession = (HttpSession) request.getHttpSession();
		Member loginMember = (Member) httpSession.getAttribute("loginMember");
		
		// @ServerEndPoint에서 접근할 수 있는 map객체 : UserProperties
		Map<String, Object> userProps = sec.getUserProperties();
		userProps.put("memberId", loginMember.getMemberId());
		
	}

}
