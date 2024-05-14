<script>
	import { Button, Modal, Textarea } from 'flowbite-svelte';
	import { onMount } from 'svelte';
	let selfIntroductionModal = false;
	let categoriesModal = false;
	let username = '';

	onMount(async () => {
		if (typeof window !== 'undefined') {
		const url = window.location.href;
		const pathSegments = new URL(url).pathname.split('/');
		username = pathSegments[1];
	};
	getUserInfo();
	});
	

	let textareapropsSelfIntroduction = {
		id: 'message',
		name: 'message',
		label: 'Your message',
		rows: 5,
		placeholder: '자기소개를 입력해주세요'
	};

	let textareapropsCategories = {
		id: 'message',
		name: 'message',
		label: 'Your message',
		rows: 5,
		placeholder: '카테고리 제목을 입력해주세요'
	};
	let selfIntroduction = '';
	let modifyIntroduction = {
		modifyIntroduction: ''
	};
	let category = {
		category: ''
	};

	let name = '';
	let categories = [];

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
				console.log(data)

				if (data.resultCode === 'S-1') {
					name = data.data.name;
					categories = data.data.categories;
					selfIntroduction = data.data.selfIntroduction;
				} else {
					alert('본인 계정의 설정만 가능합니다.');
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

	const setUserInfo = async () => {
		console.log(selfIntroduction)
		try {
			const response = await fetch(`http://localhost:8080/api/v1/${username}`, {
				method: 'PUT',
				headers: {
					'Content-Type': 'application/json'
				},
				credentials: 'include',
				body: JSON.stringify(modifyIntroduction)
			});

			if (response.ok) {
				const data = await response.json();

				if (data.resultCode === 'S-2') {
					category.category = '';
					alert('수정이 완료되었습니다');
					getUserInfo();
				} else {
					alert('본인의 계정 설정만 가능합니다.');
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

	const setCategory = async () => {
		try {
			const response = await fetch(`http://localhost:8080/api/v1/category`, {
				method: 'POST',
				headers: {
					'Content-Type': 'application/json'
				},
				credentials: 'include',
				body: JSON.stringify(category)
			});

			if (response.ok) {
				const data = await response.json();

				if (data.resultCode === 'S-1') {
					category.category = '';
					alert('추가가 완료되었습니다');
					getUserInfo();
				} else {
					alert('본인의 계정 설정만 가능합니다.');
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

	function finish() {
		location.href = `/${username}`;
	}
</script>

<div class="mx-auto w-8/12 py-16">
	<div class="flex justify-center">
		<div class="userProfileImg"></div>

		<div class="ml-8">
			<p>{name}의 페이지</p>
			<br />
			<p>{selfIntroduction}</p>
			<Button class="p-0 text-[#8AAAE5] underline" on:click={() => (selfIntroductionModal = true)}
				>수정</Button
			>
			<Modal title="자기소개 수정" bind:open={selfIntroductionModal} autoclose>
				<Textarea
					{...textareapropsSelfIntroduction}
					bind:value={modifyIntroduction.modifyIntroduction}
				/>
				<svelte:fragment slot="footer">
					<Button class="bg-[#8AAAE5]" on:click={() => setUserInfo()}>수정 완료</Button>
				</svelte:fragment>
			</Modal>
		</div>
	</div>
	<p class="mt-12 text-center">카테고리 리스트</p>
	<p class="userCategory_block mx-auto"></p>
	<div class="flex">
		<div class="mx-auto">
			{#each categories as category}
				<p>{category.name} <button class="ml-3 text-gray-300">x</button></p>
			{/each}
			<Button class="p-0 text-[#8AAAE5] underline" on:click={() => (categoriesModal = true)}
				>추가</Button
			>
			<Modal title="카테고리 추가" bind:open={categoriesModal} autoclose>
				<Textarea {...textareapropsCategories} bind:value={category.category} />
				<svelte:fragment slot="footer">
					<Button class="bg-[#8AAAE5]" on:click={() => setCategory()}>추가하기</Button>
				</svelte:fragment>
			</Modal>
			<!-- <button class="text-[#8AAAE5] underline">추가</button> -->
		</div>
	</div>
	<div class="text-right">
		<button class="mr-14 mt-4 rounded bg-[#8AAAE5] p-2 text-white" on:click={finish}
			>수정완료
		</button>
	</div>
</div>
