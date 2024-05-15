<script>
    import {
        Navbar,
        NavBrand,
        NavHamburger,
        Dropdown,
        DropdownItem,
    } from 'flowbite-svelte';
</script>

<Navbar class="px-2 sm:px-4 py-2.5 fixed w-full z-20 top-0 start-0 border-b">
    <NavBrand href="/">
        <span class="self-center whitespace-nowrap text-xl font-semibold">RECODING</span>
    </NavBrand>
    <div class="cursor-pointer" id="avatar-menu">
        <svg xmlns="http://www.w3.org/2000/svg" id="Outline" viewBox="0 0 24 24" width="20" height="20">
            <path d="M12,12A6,6,0,1,0,6,6,6.006,6.006,0,0,0,12,12ZM12,2A4,4,0,1,1,8,6,4,4,0,0,1,12,2Z"/>
            <path d="M12,14a9.01,9.01,0,0,0-9,9,1,1,0,0,0,2,0,7,7,0,0,1,14,0,1,1,0,0,0,2,0A9.01,9.01,0,0,0,12,14Z"/>
        </svg>
        <NavHamburger class1="w-full md:flex md:w-auto md:order-1"/>
    </div>
    <Dropdown placement="bottom" triggeredBy="#avatar-menu">
        <!--<DropdownHeader>
            <span class="block text-sm">Bonnie Green</span>
            <span class="block truncate text-sm font-medium">name@flowbite.com</span>
        </DropdownHeader>-->
        <DropdownItem>
            <a href="/login">↗️ 로그인 & 가입</a>
        </DropdownItem>
    </Dropdown>
</Navbar>