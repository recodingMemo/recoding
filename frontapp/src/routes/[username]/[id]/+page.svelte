<script>
	import { onMount } from 'svelte';

	import '@toast-ui/editor/dist/toastui-editor.css';
	import '@toast-ui/editor/dist/theme/toastui-editor-dark.css';

	let username = '';
	let id = '';
	let content = '';
	if (typeof window !== 'undefined') {
		const url = window.location.href;
		const pathSegments = new URL(url).pathname.split('/');

		// 경로에서 username과 id 추출
		username = pathSegments[1]; // 'cars'
		id = pathSegments[2]; // '123'

		console.log(`Username: ${username}`);
		console.log(`ID: ${id}`);
	}
	let title;
	let name;
	let markdownEditorEl;
	let markdownEditor;
	onMount(async () => {
		getPost();
	});

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
					content = data.data.post.content;
					title = data.data.post.title;
					name = data.data.post.member.name;
					const Editor = (await import('@toast-ui/editor')).default;

					markdownEditor = new Editor.factory({
						el: markdownEditorEl,
						height: '80vh',
						initialEditType: 'markdown',
						viewer: true,
						initialValue: content,
						theme: 'white'
					});

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

	function href(){
		location.href=`/${username}`
	}

</script>

<div class="mx-auto w-10/12">
	<h1 class="mt-10 text-4xl">{title}</h1>
	<div class="mt-8 flex justify-end">
		<button on:click={href} class="mr-auto">{name}</button>
		<button class="text-[#868e96]">수정</button>
		<button class="ml-2 text-[#868e96]">삭제</button>
	</div>
	<div class="mt-12">
		<div bind:this={markdownEditorEl} />
	</div>
</div>
