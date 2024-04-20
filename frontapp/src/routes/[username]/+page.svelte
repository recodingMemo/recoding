<script>
	let username = '';
	if (typeof window !== 'undefined') {
		const url = window.location.href;
		const pathSegments = new URL(url).pathname.split('/');

		username = pathSegments[1];
		console.log(`Username: ${username}`);
	}
</script>

<div class="mx-auto w-8/12 py-16">
	<!-- <div class="flex">
		<div style="background-color: blue; width:130px; height:130px; border-radius: 9999px;">
			<p class="text-white">대충 프사</p>
		</div>

		<div class="ml-16">
			<p>{username}의 페이지</p>

			<p>대충 설명란</p>
		</div>
	</div>
	<div class="userProfile_block"></div> -->
	<div class="relative">
		<section>
			<aside class="absolute" style=" width:184px; left:-200px;">
				<div class="">
					<div style="background-color: blue; width:130px; height:130px; border-radius: 9999px;">
						<p class="text-white">대충 프사</p>
					</div>

					<div class="mt-3">
						<p>{username}의 페이지</p>

						<p>대충 설명란</p>
					</div>
				</div>
				<!-- <div class="userProfile_block"></div> -->
				<p class="mt-12">카테고리 리스트</p>
				<p class="userCategory_block"></p>
				<p>대충 카테고리 1</p>
				<p>대충 카테고리 2</p>
				<p>대충 카테고리 3</p>
				<p>대충 카테고리 4</p>
			</aside>
			대충 글 목록
		</section>
	</div>
</div>
