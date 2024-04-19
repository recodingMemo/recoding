<script>
	let username = '';
	if (typeof window !== 'undefined') {
		const url = window.location.href;
		const pathSegments = new URL(url).pathname.split('/');
		// 경로에서 username과 id 추출
		username = pathSegments[1]; // 'cars'
		console.log(`Username: ${username}`);
	}
</script>

<div class="w-8/12 py-16 mx-auto">
	<div class="flex">
		<div style="background-color: blue; width:130px; height:130px;">
			<p class="text-white">대충 프사</p>
		</div>

		<div class="ml-16">
			<p>{username}의 페이지</p>

			<p>대충 설명란</p>
		</div>
	</div>
	<div class="userProfile_block"></div>
	<div class="relative">
		<section>
			<aside class="absolute" style=" width:184px; left:-200px;">
                <p>카테고리 리스트</p>
                <p class="userCategory_block"></p>
                <p> 대충 카테고리 1</p>
                <p> 대충 카테고리 2</p>
                <p> 대충 카테고리 3</p>
                <p> 대충 카테고리 4</p>
                
            </aside>
			대충 글 목록
		</section>
	</div>
</div>
