/** @type {import('tailwindcss').Config} */
const config = {
	content: ['./src/**/*.{html,js,svelte,ts}',
	'./node_modules/flowbite-svelte/**/*.{html,js,svelte,ts}',
	'./node_modules/flowbite-svelte-icons/**/*.{html,js,svelte,ts}'],
	corePlugins: {
		preflight: true,
	  },
	theme: {
	  extend: {},
	},
	plugins: [require('flowbite/plugin')],
  }
module.exports = config;
