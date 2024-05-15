<script>
	import { Input } from 'flowbite-svelte';
	import { Button, Checkbox } from 'flowbite-svelte';

	import { onMount } from 'svelte';
	import '@toast-ui/editor/dist/toastui-editor.css';
	import '@toast-ui/editor/dist/theme/toastui-editor-dark.css';

	let markdownEditorEl;
	let markdownEditor;
	let checked = '';
	let categories = [];
	onMount(async () => {
		const Editor = (await import('@toast-ui/editor')).default;
		markdownEditor = new Editor({
			el: markdownEditorEl,
			height: '80vh',
			initialEditType: 'markdown',
			previewStyle: 'vertical',
			theme: 'white'
		});
		try {
			const response = await fetch(`http://localhost:8080/api/v1/category`, {
				method: 'GET',
				headers: {
					'Content-Type': 'application/json'
				},
				credentials: 'include'
			});

			if (response.ok) {
				const data = await response.json();

				if (data.resultCode === 'S-2') {
					categories = data.data.categories;
				} else {
					alert('다시 시도 해주세요.');
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
	});

	let formData = {
		title: '',
		content: '',
		category: '카테고리 선택',
		shared: false
	};
	function check() {
		formData.content = markdownEditor.getMarkdown();
		console.log(formData);
	}
	function check2(){
		formData.content = markdownEditor.getHTML();
		console.log(formData)
	}
	function shared() {
		formData.shared = !formData.shared;
	}

	const Post = async () => {
		formData.content = markdownEditor.getMarkdown();

		//토큰 얻기
		function getCookie(name) {
			const value = `; ${document.cookie}`;
			const parts = value.split(`; ${name}=`);
			if (parts.length === 2) return parts.pop().split(';').shift();
		}
		const token = getCookie('kakaoToken');
		console.log(token);

		if (!token) {
			console.error('토큰 없음');
			return;
		}
		try {
			const response = await fetch(`http://localhost:8080/api/v1/post`, {
				method: 'POST',
				headers: {
					'Content-Type': 'application/json',
					'Authorization': `Bearer ${token}`
				},
				credentials: 'include',
				body: JSON.stringify(formData)
			});

			if (response.ok) {
				const data = await response.json();
				console.log(data)

				if (data.resultCode === 'S-4') {
					alert('게시글 등록 성공')
				} else {
					alert('본인 계정의 설정만 가능합니다.');
				}
			} else {
				// 서버에서 오류 응답 코드를 반환한 경우
				console.error('서버 응답 오류:', response.statusText);
				if (response.status === 401) {
					alert('인증되지 않았습니다. 로그인 후 다시 시도하세요.');
				} else {
					alert('서버 응답 오류가 발생했습니다. 다시 시도해주세요.');
				}
			}
		} catch (error) {
			console.error('오류 발생:', error);
			alert('서버에 요청하는 도중 오류가 발생했습니다. 다시 시도해주세요.');
		}
	};

</script>

<form>
	<div class="flex items-center mt-16">
		<form class="mr-4 max-w-sm">
			<label for="countries" class="mb-2 block text-sm font-medium text-gray-900 dark:text-white"
			></label>
			<select
				id="countries"
				bind:value={formData.category}
				class="block w-full rounded-lg border border-gray-300 bg-gray-50 p-2.5 text-sm text-gray-900 focus:border-blue-500 focus:ring-blue-500 dark:border-gray-600 dark:bg-gray-700 dark:text-white dark:placeholder-gray-400 dark:focus:border-blue-500 dark:focus:ring-blue-500"
			>
				<option selected>카테고리 선택</option>
				{#each categories as category}
					<option>{category.name}</option>
				{/each}
				<option>미선택</option>
			</select>
		</form>

		<Checkbox on:click={shared} class="mr-1" />
		<p>공개글로 설정하기</p>
	</div>
	<Input
		bind:value={formData.title}
		id="large-input"
		size="lg"
		placeholder="제목을 입력해주세요"
		class="my-3"
	/>
	<div bind:this={markdownEditorEl} />
</form>

<Button style="background-color:#8AAAE5;" on:click={Post}>게시</Button>
<Button style="background-color:#8AAAE5;" on:click={check}>확인</Button>
<Button style="background-color:#8AAAE5;" on:click={check2}>확인2</Button>

