<script>
    import {KAKAO_AUTH_URL} from "../login/OAuth";
</script>

<style>
    button.flex:hover {
        background-color: #805AD5 !important;
        color: aliceblue !important;
    }
</style>

<div class="flex text-gray-800 flex-col justify-center" style="margin-top:200px;">
    <div class="relative py-3 sm:w-96 mx-auto text-center">
        <div class="title flex" style="justify-content: center">
        <img src="/logo.png" width="200px;">
        </div>
        <div class="bg-white shadow-md rounded-lg text-left">
            <div class="h-2 bg-purple-400 rounded-t-md"></div>
            <div class="px-8 py-4 ">
                <p class="block" style="text-align: center"> RECODING에 오신 걸 환영합니다 ! ☻ </p>
                <a href={KAKAO_AUTH_URL}>
                    <div class="p-2 flex justify-center">
                        <button
                                class="flex items-center gap-x-2 rounded-md border border-slate-800 py-3 px-16"
                        >
                            <img src="https://www.talktobiz.co.kr/resources/images/ico/ico_kakao_chat.png" alt="kakao_logo" style="width: 25px;">
                            <span class="ml-2">카카오톡 로그인</span>
                        </button>

                    </div>
                </a>
            </div>
        </div>
    </div>
</div>




