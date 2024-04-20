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
		<div class="">
			<div style="background-color: blue; width:130px; height:130px; border-radius: 9999px;"></div>
			<button class="mx-auto mt-3 block rounded-sm bg-[#8AAAE5] text-white">　이미지 업로드　</button>
			<button class="mx-auto mt-3 block text-[#8AAAE5]">이미지 제거</button>
		</div>

		<div class="ml-16">
			<p>{username}</p>
			<p> 　</p>
			<p>대충 설명란</p>

			<button class="text-[#8AAAE5] underline">수정</button>
		</div>
	</div>
	<div class="userProfile_block"></div>
	<div class="relative"></div> -->

	<div class="flex justify-center">
		<div style="background-color: blue; width:200px; height:200px; border-radius: 9999px;">
			<p class="text-white">대충 프사</p>
		</div>

		<div class="ml-8">
			<p>{username}의 페이지</p>
			<p></p>
			<p>대충 설명란</p>
			<button class="text-[#8AAAE5] underline">수정</button>
		</div>
	</div>
	<!-- <div class="userProfile_block"></div> -->
	<p class="mt-12 text-center">카테고리 리스트</p>
	<p class="userCategory_block mx-auto"></p>
	<div class="flex">
		<div class="mx-auto">
			<p>대충 카테고리1 <button class="ml-3 text-gray-300">x</button></p>
			<p>대충 카테고리2 <button class="ml-3 text-gray-300">x</button></p>
			<p>대충 카테고리3 <button class="ml-3 text-gray-300">x</button></p>
			<p>대충 카테고리4 <button class="ml-3 text-gray-300">x</button></p>
			<button class="text-[#8AAAE5] underline">추가</button>
		</div>
	</div>
	<div class="text-right">
		<button class="mr-14 mt-4 p-2 bg-[#8AAAE5] text-white rounded">수정완료 </button>
	</div>
</div>
