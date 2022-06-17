<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>약관동의</title>
</head>
<body>
	<section class="section_agree">
		<div class="title">
	    	<h2>약관동의</h2>
	    	<div class="checkbox_group">
	
	          <input type="checkbox" id="check_all" >
	          <label for="check_all">전체 동의</label>
	
	          <input type="checkbox" id="check_1" class="normal" >
	          <label for="check_1">개인정보 처리방침 동의</label>
	
	          <input type="checkbox" id="check_2" class="normal" >
	          <label for="check_2">서비스 이용약관 동의</label>
	
	        </div>
		</div>
        <div class="align_btn">
          <div class="role_btn">
            <button type="submit" onClick="pw_search()">찾기</button>
          </div>
        </div>
	</section>
</body>
</html>
<script>
$(".checkbox_group").on("click", "#check_all", function () {
    $(this).parents(".checkbox_group").find('input').prop("checked", $(this).is(":checked"));
});

// 체크박스 개별 선택
$(".checkbox_group").on("click", ".normal", function() {
    var is_checked = true;

    $(".checkbox_group .normal").each(function(){
        is_checked = is_checked && $(this).is(":checked");
    });

    $("#check_all").prop("checked", is_checked);
});
</script>