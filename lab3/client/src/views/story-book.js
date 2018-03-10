export default function (store) {
	return class StoryBookComponent extends window.HTMLElement {
		constructor () {
			super();
			this.store = store;
			this.render();
			this.onStateChange = this.handleStateChange.bind(this);
		}

		handleStateChange (newState) {
			// TODO: display story based on the state "resource" and "stories"
			// console.log('newState: ', newState.story);
			this.store.state.storys.forEach((story) => {
				// story.isUnlockYet(this.store.state.counter)
				if(this.store.state.counter > story.triggeredAt){
					this.addText(story.description);
					this.disconnectedCallback();
				}
			});
			this.render();
		}

		addText(text) {
			console.log(text);
			this.boxMessage += ('\n' + text);
		}

		connectedCallback () {
			this.render();
			this.store.subscribe(this.onStateChange);
		}

		disconnectedCallback () {
			this.render();
			this.store.unsubscribe(this.onStateChange);
		}

		render () {
			const boxMessage = "The Story Begins...";
			this.innerHTML = `<div>
			<textarea class="scrollabletextbox" readonly="true">${this.boxMessage}</textarea>
		</div>`;
		}
	};
}

