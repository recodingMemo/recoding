<script>
	import { onMount } from 'svelte';

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

	const getPost = async () => {
		try {
			const response = await fetch(`http://localhost:8080/api/v1/${username}/${id}`, {
				method: 'GET',
				headers: {
					'Content-Type': 'application/json'
				},
				credentials: 'include'
			});

			if (response.ok) {
				const data = await response.json();

				if (data.resultCode === 'S-2') {
				} else {
					alert('등록된 글이 없습니다.');
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

	
	let markdownViewerEl;
	let markdownViewer;

	async function viewer() {
		const Viewer = (await import('@toast-ui/editor/dist/toastui-editor-viewer')).default;
		markdownViewer = new Viewer({
			el: markdownViewerEl,
			initialValue: checked
		});
	}


</script>

<div class="w-10/12 mx-auto">
	<h1 class="mt-10 text-4xl">제목</h1>
	<div class="flex justify-end mt-8">
		<button class="mr-auto">글쓴이</button>
		<button class="text-[#868e96]">수정</button>
		<button class="ml-2 text-[#868e96]">삭제</button>
	</div>
	<div class="mt-12">
		내용
	</div>
</div>
<div bind:this={markdownViewerEl} />