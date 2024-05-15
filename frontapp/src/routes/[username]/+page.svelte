<script>
	const nickname = decodeURIComponent('${nickname}');

	let username = '';
	if (typeof window !== 'undefined') {
		const url = window.location.href;
		const pathSegments = new URL(url).pathname.split('/');
		// 경로에서 username과 id 추출
		username = pathSegments[1];
		console.log(nickname);
		document.getElementById('usernameContainer').innerHTML = `<p>{username}의 페이지</p>`;
		console.log(`Username: ${username}`);
	}
</script>

<main class="profile-page">
	<section class="relative block h-500-px">
		<div class="absolute top-0 w-full h-full bg-center bg-cover">
			<span id="blackOverlay" class="w-full h-full absolute bg-blue-800"></span>
		</div>
		<div class="top-auto bottom-0 left-0 right-0 w-full absolute pointer-events-none overflow-hidden h-70-px" style="transform: translateZ(0px)">
			<svg class="absolute bottom-0 overflow-hidden" xmlns="http://www.w3.org/2000/svg" preserveAspectRatio="none" version="1.1" viewBox="0 0 2560 100" x="0" y="0">
				<polygon class="text-blueGray-200 fill-current" points="2560 0 2560 100 0 100"></polygon>
			</svg>
		</div>
	</section>
	<section class="relative py-16 bg-blueGray-200">
		<div class="container mx-auto px-4">
			<div class="relative flex flex-col bg-white w-full mb-6 shadow-xl rounded-lg -mt-64">
				<div class="px-6">
					<div class="flex flex-wrap justify-center">
						<div class="w-full lg:w-3/12 px-4 lg:order-2 flex justify-center">
							<div class="relative">
								<img alt="..." src="/pro_img.png" class="shadow-xl rounded-full h-auto align-middle border-none absolute -m-16 -ml-20 lg:-ml-16 max-w-150-px">
							</div>
						</div>
						<div class="w-full lg:w-4/12 px-4 lg:order-3 lg:text-right lg:self-center">
						</div>
						<div class="w-full lg:w-4/12 px-4 lg:order-1">
							<div class="flex justify-center py-4 lg:pt-4 pt-8">
								<div class="mr-4 p-3 text-center">
									<span class="text-xl font-bold block uppercase tracking-wide text-blueGray-600">22</span><span class="text-sm text-blueGray-400">전체 게시글</span>
								</div>
								<div class="mr-4 p-3 text-center">
									<span class="text-xl font-bold block uppercase tracking-wide text-blueGray-600">10</span><span class="text-sm text-blueGray-400">방문자 수</span>
								</div>
							</div>
						</div>
					</div>
					<div class="text-center mt-8">
						<h3 class="text-4xl font-semibold leading-normal mb-2 text-blueGray-700 ml-6">
							김조은
						</h3>
						<div class="ml-2 mb-2 text-blueGray-600 mt-10">
							<i class="fas fa-briefcase mr-2 text-lg text-blueGray-400"></i>(주)대충 회사 연구원
						</div>
						<div class="ml-2 text-blueGray-600">
							<i class="fas fa-university mr-2 text-lg text-blueGray-400"></i>대충 대학 졸업
						</div>
					</div>
					<div class="mt-10 py-10 border-t border-blueGray-200 text-center">
						<div class="flex flex-wrap justify-center">
							<div class="w-full lg:w-9/12 px-4">
								<p class="mb-4 text-lg leading-relaxed text-blueGray-700">
									자기 소개 입력란
								</p>
							</div>
						</div>
					</div>

				</div>
			</div>
		</div>
		<footer class="relative bg-blueGray-200 pt-8 pb-6 mt-8">
			<div class="container mx-auto px-4">
				<div class="flex flex-wrap items-center md:justify-between justify-center">
					<div class="w-full md:w-6/12 px-4 mx-auto text-center">
						<div class="text-sm text-blueGray-500 font-semibold py-1">
							Made by <a href="https://www.creative-tim.com" class="text-blueGray-500 hover:text-blueGray-800" target="_blank"> Recoding</a>.
						</div>
					</div>
				</div>
			</div>
		</footer>
	</section>
</main>

<!--<div class="w-8/12 py-16 mx-auto">-->
<!--	<div class="flex">-->
<!--		<div style="background-color: blue; width:130px; height:130px;">-->
<!--			<p class="text-white">대충 프사</p>-->
<!--		</div>-->

<!--		<div class="ml-16">-->
<!--			<p>{username}의 페이지</p>-->

<!--			<p>대충 설명란</p>-->
<!--		</div>-->
<!--	</div>-->
<!--	<div class="userProfile_block"></div>-->
<!--	<div class="relative">-->
<!--		<section>-->
<!--			<aside class="absolute" style=" width:184px; left:-200px;">-->
<!--                <p>카테고리 리스트</p>-->
<!--                <p class="userCategory_block"></p>-->
<!--                <p> 대충 카테고리 1</p>-->
<!--                <p> 대충 카테고리 2</p>-->
<!--                <p> 대충 카테고리 3</p>-->
<!--                <p> 대충 카테고리 4</p>-->

<!--            </aside>-->
<!--			대충 글 목록-->
<!--		</section>-->
<!--	</div>-->
<!--</div>-->
