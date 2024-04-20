<script>
	import { Input } from 'flowbite-svelte';
	import { Textarea } from 'flowbite-svelte';
	import { Button, Dropdown, DropdownItem, Checkbox } from 'flowbite-svelte';
	import { ChevronDownOutline } from 'flowbite-svelte-icons';
	let formData = {
		title: '',
		content: ''
	};
	let textareaprops = {
		id: 'message',
		name: 'message',
		label: 'Your message',
		rows: 20,
		placeholder: '내용을 입력해주세요'
	};
	function check() {
		console.log(formData);
	}
</script>

<form>
	<div class="flex items-center">
		<Button class="my-3 mr-5 bg-[#8AAAE5]"
			>카테고리<ChevronDownOutline class="ms-2 h-6 w-6 text-white dark:text-white" /></Button
		>
		<Dropdown>
			<div slot="header" class="px-4 py-2">
				<span class="block text-sm text-gray-900 dark:text-white">Bonnie Green</span>
				<span class="block truncate text-sm font-medium">name@flowbite.com</span>
			</div>
			<DropdownItem>Dashboard</DropdownItem>
			<DropdownItem>Settings</DropdownItem>
			<DropdownItem>Earnings</DropdownItem>
			<DropdownItem slot="footer">Sign out</DropdownItem>
		</Dropdown>

		<Checkbox class="mr-1"/><p>공개글로 설정하기</p>
	</div>
	<Input
		bind:value={formData.title}
		id="large-input"
		size="lg"
		placeholder="제목을 입력해주세요"
		class="my-3"
	/>
	<Textarea bind:value={formData.content} {...textareaprops} class="my-3" />
</form>

<Button class="bg-[#8AAAE5]">게시</Button>
