<script>
	import '../app.css';
	import { Navbar, NavBrand, NavLi, NavUl, NavHamburger } from 'flowbite-svelte';
</script>

<Navbar>
	<NavBrand href="/">
		<img src="/images/flowbite-svelte-icon-logo.svg" class="me-3 h-6 sm:h-9" alt="대충 로고" />
		<span class="self-center whitespace-nowrap text-xl font-semibold dark:text-white">레코딩</span>
	</NavBrand>
	<NavHamburger />
	<NavUl>
		<!-- access토큰 있으면 로그아웃으로 바꿔주면 될듯 -->
		<NavLi href="/">로그인</NavLi>
	</NavUl>
</Navbar>
<div class="flex mx-auto w-10/12">
	<slot></slot>
</div>
