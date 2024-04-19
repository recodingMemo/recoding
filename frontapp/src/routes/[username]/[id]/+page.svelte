<script>
	let username = '';
	let id = '';
	if (typeof window !== 'undefined') {
		const url = window.location.href;
		const pathSegments = new URL(url).pathname.split('/');

		// 경로에서 username과 id 추출
		username = pathSegments[1]; // 'cars'
		id = pathSegments[2]; // '123'

		console.log(`Username: ${username}`);
		console.log(`ID: ${id}`);
	}
</script>

<div>{username}의 {id}게시물임</div>
