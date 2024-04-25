<script>
    import { KAKAO_AUTH_URL } from "../login/OAuth";
</script>

<style>
    .sub-title {
        margin-left: 170px;
    }

</style>
<div class="bg-white h-screen overflow-hidden flex items-center justify-center">
    <div class="rounded-2xl bg-[#8AAAE5] lg:w-5/12 md:6/12 w-10/12 shadow-3xl">
        <div class="flex justify-center">
            <img class="" src="/logo.png" alt="이미지 설명" style="width:150px;"/>
        </div>
        <span class="sub-title text-amber-50">RECODING에 오신 걸 환영합니다 !</span>
        <a href={KAKAO_AUTH_URL}>
            <div class="p-2">
                <button class="flex bg-[#FEE500] from-gray-700 to-gray-900 font-medium rounded-2xl md:p-4 text-[#000000] uppercase w-full">
                    <img src="https://upload.wikimedia.org/wikipedia/commons/thumb/d/dd/Kakao_Corp._symbol_-_2012.svg/2226px-Kakao_Corp._symbol_-_2012.svg.png"
                         alt="아이콘" style="width: 30px;">
                    <span class="login-text ml-40 font-bold">카카오톡으로 로그인 하기</span>
                </button>
            </div>
        </a>
    </div>
</div>
