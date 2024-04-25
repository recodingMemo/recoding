const CLIENT_ID = "7a25804a9481a9220c7c6941e772c6ef";
const REDIRECT_URI =  "http://localhost:5173/oauth/callback/kakao";

export const KAKAO_AUTH_URL = `https://kauth.kakao.com/oauth/authorize?client_id=${CLIENT_ID}&redirect_uri=${REDIRECT_URI}&response_type=code`;