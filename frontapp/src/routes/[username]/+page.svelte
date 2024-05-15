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

	//url 디코딩 시켜 유저네임 나오게 하기
	const decodedUsername = decodeURIComponent(username);
	console.log(`username: ${decodedUsername}`);
	name = decodedUsername;

	onMount(async () => {
		//토큰 얻기
		function getCookie(name) {
			const value = `; ${document.cookie}`;
			const parts = value.split(`; ${name}=`);
			if (parts.length === 2) return parts.pop().split(';').shift();
		}
		const token = getCookie('kakaoToken');
		console.log(token);

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
<style>
	.w-10\/12 {width: 100%;}
</style>

<!--<div class="mx-auto w-8/12 py-16">-->
<!--	<div class="relative">-->
<!--		<section>-->
<!--			<aside class="absolute" style=" width:184px; left:-200px;">-->
<!--				<div class="">-->
<!--					<div style="background-color: blue; width:130px; height:130px; border-radius: 9999px;">-->
<!--						<p class="text-white">대충 프사</p>-->
<!--					</div>-->

<!--					<div class="mt-3">-->
<!--						<p>{name}의 페이지</p>-->
<!--						<br />-->
<!--						<p>{selfIntroduction}</p>-->
<!--					</div>-->
<!--				</div>-->
<!--				&lt;!&ndash; <div class="userProfile_block"></div> &ndash;&gt;-->
<!--				<p class="mt-12">카테고리 리스트</p>-->
<!--				<p class="userCategory_block"></p>-->
<!--				<button class="mb-2" on:click={() => getUserPost('total')}>전체보기</button><br/>-->
<!--				{#each categories as category}-->
<!--					<button on:click={() => getUserPost(category.name)}>{category.name} </button><br/>-->
<!--				{/each}-->
<!--			</aside>-->
<!--			<div class="grid grid-cols-5">-->
<!--				{#each userPosts as post}-->
<!--					<a href="/{username}/{post.id}">{post.title}</a>-->
<!--				{/each}-->
<!--			</div>-->
<!--		</section>-->
<!--	</div>-->
<!--</div>-->
<!-- component -->
<div id="body" class="max-w-full bg-slate-50 mt-16 h-screen flex">
	<nav class="bg-white flex flex-col gap-10 border-r border-slate-100">
		<div class="user flex items-center justify-center flex-col gap-4 border-b border-emerald-slate-50">
			<img class="w-24 rounded-full shadow-xl" src="https://w7.pngwing.com/pngs/340/946/png-transparent-avatar-user-computer-icons-software-developer-avatar-child-face-heroes-thumbnail.png">
			<div class="flex flex-col items-center">
				<span class="font-semibold text-lg text-emerald-700">{name}</span>
				<span class="text-slate-400 text-sm">직업 입력</span>
			</div>
			<div class="text-sm text-slate-400">
				<span class="font-semibold text-slate-500">한줄 소개 입력</span>
			</div>
		</div>

		<ul class="px-6 space-y-2">
			<li>
			<a class="block px-4 py-2.5 text-slate-800 font-semibold  hover:bg-emerald-950 hover:text-white rounded-lg" href="#">Haber Yönetimi</a>
		</li>
			<li>
				<a class="block px-4 py-2.5 text-slate-800 font-semibold hover:bg-emerald-950 hover:text-white rounded-lg" href="#">CMS Hesapları</a>

			</li>
			<li>
				<a class="block px-4 py-2.5 text-slate-800 font-semibold hover:bg-emerald-950 hover:text-white rounded-lg" href="#">Destek Talepleri</a>
			</li>
			<li class="bg-slate-50 pb-2 rounded-lg border">
				<a class="block px-4 py-2.5 text-slate-200 font-semibold hover:bg-emerald-950 hover:text-white rounded-lg bg-emerald-950" href="#">Loglar & Kayıtlar</a>
				<ol class="text-sm text-slate-700 space-y-4 pl-6 my-2.5">

				</ol>
			</li>
			<li>
				<a class="block px-4 py-2.5 text-slate-800 font-semibold hover:bg-emerald-950 hover:text-white rounded-lg" href="#">Etkinlik Yönetimi</a>
			</li>
		</ul>
	</nav>
	<a href="/write">글쓰기</a>
</div>
