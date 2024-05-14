<script>
	import { onMount } from 'svelte';
	let username = '';
	if (typeof window !== 'undefined') {
		const url = window.location.href;
		const pathSegments = new URL(url).pathname.split('/');

		username = pathSegments[1];
		console.log(`Username: ${username}`);
	}

	let name = '';
	let categories = [];
	let selfIntroduction = '';
	onMount(async () => {
		if (typeof window !== 'undefined') {
		const url = window.location.href;
		const pathSegments = new URL(url).pathname.split('/');
		username = pathSegments[1];
	};
	getUserInfo();
	getUserPost('total');
	});

	const getUserInfo = async () => {
		try {
			const response = await fetch(`http://localhost:8080/api/v1/${username}`, {
				method: 'GET',
				headers: {
					'Content-Type': 'application/json'
				},
				credentials: 'include'
			});

			if (response.ok) {
				const data = await response.json();

				if (data.resultCode === 'S-1') {
					name = data.data.name;
					categories = data.data.categories;
					selfIntroduction = data.data.selfIntroduction;
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

	let userPosts = [];
	const getUserPost = async (type) => {
		try {
			const response = await fetch(
				`http://localhost:8080/api/v1/${username}/post?category=${type}`,
				{
					method: 'GET',
					headers: {
						'Content-Type': 'application/json'
					},
					credentials: 'include'
				}
			);

			if (response.ok) {
				const data = await response.json();

				if (data.resultCode === 'S-1') {
					console.log(data)
					userPosts = data.data.posts;
				} else {
					alert('아이디/카테고리가 잘못되었습니다. 확인해 주세요.');
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

	function check(random) {
		console.log(random);
	}
</script>

<div class="mx-auto w-8/12 py-16">
	<div class="relative">
		<section>
			<aside class="absolute" style=" width:184px; left:-200px;">
				<div class="">
					<div style="background-color: blue; width:130px; height:130px; border-radius: 9999px;">
						<p class="text-white">대충 프사</p>
					</div>

					<div class="mt-3">
						<p>{name}의 페이지</p>
						<br />
						<p>{selfIntroduction}</p>
					</div>
				</div>
				<!-- <div class="userProfile_block"></div> -->
				<p class="mt-12">카테고리 리스트</p>
				<p class="userCategory_block"></p>
				<button class="mb-2" on:click={() => getUserPost('total')}>전체보기</button><br/>
				{#each categories as category}
					<button on:click={() => getUserPost(category.name)}>{category.name} </button><br/>
				{/each}
			</aside>
			<div class="grid grid-cols-5">
				{#each userPosts as post}
					<a href="/{username}/{post.id}">{post.title}</a>
				{/each}
			</div>
		</section>
	</div>
</div>
