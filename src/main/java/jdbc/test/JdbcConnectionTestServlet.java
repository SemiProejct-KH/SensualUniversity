package jdbc.test;

import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class JdbcConnectionTestServlet
 */
@WebServlet("/test/dbConnection")
public class JdbcConnectionTestServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	String driverClass = "oracle.jdbc.OracleDriver"; // 드라이버 클래스명
	String url = "jdbc:oracle:thin:@semiproject_medium?TNS_ADMIN=C:/Wallet/Wallet_semiproject"; // 접속할 db서버 주소 (db접속 프로토콜 @ip:port:sid)
	String user = "admin";
	String password = "Semi12345678";
	
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// db 연결 테스트
		test();
		
		response.setContentType("text/plain; charset=utf-8");
		response.getWriter().append("db 연결 테스트 - 서버 콘솔을 확인하세요 !");
	}
	
	/**
	 * DQL
	 * - select
	 */
	public void test() {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = "select * from su_member";
		
		try {
			// 1. jdbc driver class 등록
			Class.forName(driverClass);
			System.out.println("> driver class 등록 완료 !");
			
			// 2. Connection객체 생성(url, user, password)
			conn = DriverManager.getConnection(url, user, password);
			System.out.println("> Connection객체 생성 성공 !");

			// 3. PreparedStatement객체 (쿼리 실행 객체) 생성 & 미완성 쿼리 값 대입
			pstmt = conn.prepareStatement(sql);
			System.out.println("> PreparedStatement객체 생성 성공 !");
			
			// 4. PreparedStatement 실행 - ResultSet객체 반환
			rset = pstmt.executeQuery();
			System.out.println("> PreparedStatement 실행 및 ResultSet 반환 성공 ! ");
			
			// 5. ResultSet을 1행씩 열람
			// 다음행이 존재하면 true 리턴
			while(rset.next()) {
				String id = rset.getString("member_id");
				String name = rset.getString("member_name");
				Date birthday = rset.getDate("member_birth");
				System.out.printf("%s\t%s\t%s\n", id, name, birthday);
			}
			
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		} finally {
			// 6. 자원 반납
			// 객체생성 역순
			try {
				rset.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
			try {
				pstmt.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
			try {
				conn.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
			System.out.println("> 자원반납 성공 !");
		}
	}
// 123 321 123 312 122 312 123
}
