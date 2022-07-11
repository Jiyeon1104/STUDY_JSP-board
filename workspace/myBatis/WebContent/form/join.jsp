<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%request.setCharacterEncoding("UTF-8"); %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>회원가입</title>
</head>
<body>
    <form action="joinDB.jsp" name="joinForm" method="post">
        <p>
            <label>
                아이디 : <input type="text" name="memberId">
            </label>
            <input type="button" value="중복확인" onclick="checkId()">
        </p>
        <p id="result"></p>
        <p>
            <label>
                이름 : <input type="text" name="memberName">
            </label>
        </p>
        <p>
            <label>
                비밀번호 : <input type="password" name="memberPw">
            </label>
        </p>
        <p id="pwCheck"></p>
        <p>
            <label>
                비밀번호 확인 : <input type="password" name="rePw">
            </label>
        </p>
        <p id="pwReCheck"></p>
        <p>
            성별 : 
            <label>남자 <input type="radio" name="gender" value="M"></label>
            <label>여자 <input type="radio" name="gender" value="W"></label>
            <label>선택안함 <input type="radio" name="gender" value="N"></label>
        </p>
        <p>
            <input type="text" name="address" id="address" placeholder="주소" readonly>
            <input type="button" onclick="sample5_execDaumPostcode()" value="주소 검색"><br>
            <input type="text" name="addressDetail" placeholder="상세 주소">
        </p>
        <p>
            <input type="button" value="가입 완료" onclick="join()">
        </p>
    </form>
</body>
<div id="map" style="width:300px;height:300px;margin-top:10px;display:none"></div>
<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<script src="//dapi.kakao.com/v2/maps/sdk.js?appkey=dddcfaf76dc282d71df78320125838b8&libraries=services"></script>
<script src="join.js"></script>
<script>
    var mapContainer = document.getElementById('map'), // 지도를 표시할 div
        mapOption = {
            center: new daum.maps.LatLng(37.537187, 127.005476), // 지도의 중심좌표
            level: 5 // 지도의 확대 레벨
        };

    //지도를 미리 생성
    var map = new daum.maps.Map(mapContainer, mapOption);
    //주소-좌표 변환 객체를 생성
    var geocoder = new daum.maps.services.Geocoder();
    //마커를 미리 생성
    var marker = new daum.maps.Marker({
        position: new daum.maps.LatLng(37.537187, 127.005476),
        map: map
    });


    function sample5_execDaumPostcode() {
        new daum.Postcode({
            oncomplete: function(data) {
                var addr = data.address; // 최종 주소 변수

                // 주소 정보를 해당 필드에 넣는다.
                document.getElementById("address").value = addr;
                // 주소로 상세 정보를 검색
                geocoder.addressSearch(data.address, function(results, status) {
                    // 정상적으로 검색이 완료됐으면
                    if (status === daum.maps.services.Status.OK) {

                        var result = results[0]; //첫번째 결과의 값을 활용

                        // 해당 주소에 대한 좌표를 받아서
                        var coords = new daum.maps.LatLng(result.y, result.x);
                        // 지도를 보여준다.
                        mapContainer.style.display = "block";
                        map.relayout();
                        // 지도 중심을 변경한다.
                        map.setCenter(coords);
                        // 마커를 결과값으로 받은 위치로 옮긴다.
                        marker.setPosition(coords)
                    }
                });
            }
        }).open();
    }
</script>
</html>