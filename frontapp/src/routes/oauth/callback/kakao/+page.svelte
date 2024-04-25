<script>
    import {onMount} from 'svelte';
    import {comment} from "postcss";

    onMount(async () => {
        // URL에서 인증 코드(code) 추출.
        const urlParams = new URLSearchParams(window.location.search);

        // code 라는 변수에 카카오에서 발급한 코드를 넣음.
        const code = urlParams.get('code');

        if (code) {
            // 인증 코드가 유효하면, 이를 서버에 전송
            const response = await fetch(`https://kauth.kakao.com/oauth/token`, {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/x-www-form-urlencoded;charset=utf-8'
                },
                body: new URLSearchParams({
                    grant_type: 'authorization_code',
                    client_id: import.meta.env.VITE_CLIENT_ID,
                    redirect_uri: 'http://localhost:5173/oauth/callback/kakao',
                    code: code
                })
            });
            // data 라는 변수에 서버에서 받아온 정보 response를 json 형태로 저장시킴.
            const data = await response.json();
            // data에서 액세스 토큰 추출.
            const access_token = data.access_token;
            console.log(access_token);

            // 추출한 액세스 토큰으로 다시 카카오 서버에 유저 정보 요청
            const userInfoResponse = await fetch('https://kapi.kakao.com/v2/user/me', {
                method: 'GET',
                headers: {
                    Authorization: `Bearer ${access_token}`
                }
            });

            // 서버에서 받아온 userInfoResponse 변수에 담긴 ok값이 true라면
            if (userInfoResponse.ok) {
                // userInfo 라는 변수에 userInfoResponse를 json 타입으로 저장시킴.
                const userInfo = await userInfoResponse.json();
                console.log(userInfo);

                // formData 라는 변수에 카카오 서버에서 받아온 유저 고유의 id값, 닉네임값을 저장시킴.
                let formData = {
                    id: `${userInfo.id}`,
                    nickname: `${userInfo.properties.nickname}`
                };
                console.log(formData);

                // formData를 백엔드 서버로 보내, 로그인 요청 시도
                const res = await fetch(`http://localhost:8080/api/v1/member/kakaologin`, {
                    method: 'POST',
                    headers: {
                        'Content-Type': 'application/json'
                    },
                    body: JSON.stringify(formData)
                });

                // 백엔드 서버에서 받은 res 변수에 담긴 ok값이 true 라면
                if (res.ok) {
                    const data = await res.json();

                    // 액세스 토큰과 리프레시 토큰을 각각 변수에 저장시키고
                    const accesstoken = await data.data.accessToken;
                    const refreshToken = await data.data.refreshToken;

                    // 두 토큰을 로컬 스토리지에 저장 후 홈 페이지로 이동
                    await localStorage.setItem('accessToken', accesstoken);
                    await localStorage.setItem('refreshToken', refreshToken);
                    window.location.href = '/';
                }else {
                    console.log(res.statusText);
                }
            }
        }
    });
</script>