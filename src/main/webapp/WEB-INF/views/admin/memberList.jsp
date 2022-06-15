<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/header.jsp" %>

<section class="section">
<div class="content-body">
    <div class="container-fluid">
        <h2 align="center" style="margin-top:100px;">회원관리</h2>	
        <div class="row">
            <div class="col-12">
                <nav style="--bs-breadcrumb-divider: '|';" aria-label="breadcrumb">
                    <ol class="breadcrumb">
                        <li class="breadcrumb-item"><a href="#" style="text-decoration-line: none;">홈</a></li>
                        <li class="breadcrumb-item active" aria-current="page"><a href="#" style="text-decoration-line: none;">학생관리</a></li>
                        <li class="breadcrumb-item active" aria-current="page"><a href="#" style="text-decoration-line: none;">교사관리</a></li>
                    </ol>
                </nav>
                <div class="card">
                    <div class="card-header">
                        <nav style="--bs-breadcrumb-divider: '|';" aria-label="breadcrumb" style="margin-top: 20px">
                            <h4 class="card-title" style="font-weight:bold;">학생 목록</h4>
                        </nav>
                    </div>
                    <div class="card-body">
                        <div class="table-responsive">
                            <table id="example2" class="table" style="width: 100%">
                                <thead class="table-light" style="border-bottom:1px solid gray; padding:10px;">
                                    <tr style="border-bottom:1px solid gray; padding:10px;">
                                        <th>아이디</th>
                                        <th>이름</th>
                                        <th>학과</th>
                                        <th>학년</th>
                                        <th>생년월일</th>
                                        <th>핸드폰</th>
                                        <th>이메일</th>
                                        <th>가입일</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <!-- 리스트 반복문 -->
                                        <tr>
                                            <td>honggd</td>
                                            <td>길동이</td>
                                            <td>생활체육과</td>
                                            <td>1학년</td>
                                            <td>97/01/01</td>
                                            <td>01012341234</td>
                                            <td>honggd1234@naver.com</td>
                                            <td>22/06/14</td>
                                        </tr>
                                        <tr>
                                            <td>honggd</td>
                                            <td>길동이</td>
                                            <td>생활체육과</td>
                                            <td>1학년</td>
                                            <td>97/01/01</td>
                                            <td>01012341234</td>
                                            <td>honggd1234@naver.com</td>
                                            <td>22/06/14</td>
                                        </tr>
                                </tbody>
                            </table>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
</section>

<%@ include file="/WEB-INF/views/common/footer.jsp" %>