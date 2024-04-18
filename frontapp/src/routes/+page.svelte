<script>
	import { AccordionItem, Accordion } from 'flowbite-svelte';
    import { Card } from 'flowbite-svelte';
</script>

<div>
	<Accordion>
		<AccordionItem>
			<span slot="header">My Header 1</span>
			<p class="mb-2 text-gray-500 dark:text-gray-400">
				Lorem ipsum dolor sit amet, consectetur adipisicing elit. Illo ab necessitatibus sint
				explicabo ...
			</p>
			<p class="text-gray-500 dark:text-gray-400">
				Check out this guide to learn how to <a
					href="/"
					target="_blank"
					rel="noreferrer"
					class="text-blue-600 dark:text-blue-500 hover:underline"
				>
					get started
				</a>
				and start developing websites even faster with components on top of Tailwind CSS.
			</p>
		</AccordionItem>
		<AccordionItem>
			<span slot="header">My Header 2</span>
			<p class="mb-2 text-gray-500 dark:text-gray-400">
				Lorem ipsum dolor sit amet, consectetur adipisicing elit. Illo ab necessitatibus sint
				explicabo ...
			</p>
			<p class="mb-2 text-gray-500 dark:text-gray-400">
				Lorem ipsum dolor sit amet, consectetur adipisicing elit. Illo ab necessitatibus sint
				explicabo ...
			</p>
			<p class="mb-2 text-gray-500 dark:text-gray-400">Learn more about these technologies:</p>
			<ul class="list-disc ps-5 dark:text-gray-400 text-gray-500">
				<li>
					<a
						href="/"
						target="_blank"
						rel="noreferrer"
						class="text-blue-600 dark:text-blue-500 hover:underline"
					>
						Lorem ipsum
					</a>
				</li>
				<li>
					<a
						href="https://tailwindui.com/"
						rel="noreferrer"
						target="_blank"
						class="text-blue-600 dark:text-blue-500 hover:underline"
					>
						Tailwind UI
					</a>
				</li>
			</ul>
		</AccordionItem>
	</Accordion>
</div>
<div>
	<Card href="/cards">
		<h5 class="mb-2 text-2xl font-bold tracking-tight text-gray-900 dark:text-white">
			Noteworthy technology acquisitions 2021
		</h5>
		<p class="font-normal text-gray-700 dark:text-gray-400 leading-tight">
			Here are the biggest enterprise technology acquisitions of 2021 so far, in reverse
			chronological order.
		</p>
	</Card>
</div>
