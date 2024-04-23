<script>
    let posts = [];
	const getUserPost = async (type) => {
		try {
			const response = await fetch(`http://localhost:8080/api/v1/list`, {
				method: 'GET',
				headers: {
					'Content-Type': 'application/json'
				},
				credentials: 'include'
			});

			if (response.ok) {
				const data = await response.json();

				if (data.resultCode === 'S-1') {
					posts = data.data.posts;
				} else {
				}
			} else {
				console.error('서버 응답 오류:', response.statusText);
				if (!response.ok && response.status != 401) {
					alert('다시 시도 해주세요.');
				}
			}
		} catch (error) {
			console.error('오류 발생:', error);
			alert('다시 시도 해주세요.');
		}
	};
</script>
<div class="grid grid-cols-5">
    {#each posts as post}
        <a href="/{post.username}/{post.id}">{post.title}</a>
    {/each}
</div>
공개글 리스트임
