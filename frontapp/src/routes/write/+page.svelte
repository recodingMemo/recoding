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
		// try {
		// 	const response = await fetch(
		// 		`http://localhost:8080/api/v1/category`,
		// 		{
		// 			method: 'GET',
		// 			headers: {
		// 				'Content-Type': 'application/json'
		// 			},
		// 			credentials: 'include'
		// 		}
		// 	);

		// 	if (response.ok) {
		// 		const data = await response.json();

		// 		if (data.resultCode === 'S-2') {
					
		// 		} else {
		// 			alert('다시 시도 해주세요.');
		// 		}
		// 	} else {
		// 		console.error('서버 응답 오류:', response.statusText);
		// 		if (!response.ok && response.status != 401) {
		// 			alert('다시 시도 해주세요.');
		// 		}
		// 	}
		// } catch (error) {
		// 	console.error('오류 발생:', error);
		// 	alert('다시 시도 해주세요.');
		// }

	});


	let formData = {
		title: '',
		content: '',
		category:'',
		shared:''
	};
	function check(){
		console.log(1);
	}
	
</script>

<form>
	<div class="flex items-center">
		<form class="mr-4 max-w-sm ">
			<label for="countries" class="mb-2 block text-sm font-medium text-gray-900 dark:text-white"
				></label
			>
			<select
				id="countries"
				class="block w-full rounded-lg border border-gray-300 bg-gray-50 p-2.5 text-sm text-gray-900 focus:border-blue-500 focus:ring-blue-500 dark:border-gray-600 dark:bg-gray-700 dark:text-white dark:placeholder-gray-400 dark:focus:border-blue-500 dark:focus:ring-blue-500"
			>
				<option selected>카테고리 선택</option>
				<!-- {#each  as }
					
				{/each} -->
			
			</select>
		</form>

		<Checkbox class="mr-1" />
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

<Button class="bg-[#8AAAE5]" on:click={check}>게시</Button>


