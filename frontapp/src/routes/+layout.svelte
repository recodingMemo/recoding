<script>
    import '../app.css';
    import Navbar from './components/navbar.svelte';
    import {afterUpdate} from "svelte";

    let showNav = false;

    afterUpdate(() => {
        const currentPath = window.location.pathname;

        if (currentPath === '/login') {
            showNav = false;
        } else {
            showNav = true;
        }
    });
</script>

<div class="mx-auto w-10/12">
    <div class="mx-auto w-10/12">
        {#if showNav}
            <Navbar/>
            <slot/>
        {:else}
            <slot/>
        {/if}
    </div>
</div>