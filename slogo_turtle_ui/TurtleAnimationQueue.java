package slogo_turtle_ui;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Handles the turtle animation queue with ability to add to queue and play queue.
 *
 */

public class TurtleAnimationQueue {
	
	private Queue<TurtleAnimation> queue;
	private Animator animator;
	private TurtleAnimation lastAnimationQueued;
	private boolean animationsDonePlaying;
	private TurtleAnimation runningAnimation;
	
	public TurtleAnimationQueue (Animator animator, TurtleAnimation runningAnimation) {
		this.runningAnimation = runningAnimation;
		this.queue = new LinkedList<TurtleAnimation>();
		this.animator = animator;
		this.animationsDonePlaying = true;
	}
	
	/**
	 *  Plays animations that are queued up.
	 *  If animations are still playing, queues up animations to end of
	 *  currently playing queue of animations.
	 */
	public void playQueuedAnimations() {
		if (!queue.isEmpty()) {
			TurtleAnimation head = animationsDonePlaying ? queue.poll() : lastAnimationQueued;
			TurtleAnimation curr = head;
			while (!queue.isEmpty()) {
				TurtleAnimation next = queue.poll();
				curr.setNext(next);
				next.setPrev(curr);
				if (next.isPendown()) {	
					curr.getAnimation().setOnFinished((event)-> {
						animator.endLine();
						animator.bindLineToTurtle(next.getAnimation());
						runningAnimation = next;
						next.getAnimation().play();
						});
				} else {
					curr.getAnimation().setOnFinished((event)-> {
						animator.endLine();
						runningAnimation = next;
						next.getAnimation().play();
						});
				}
				curr = next;
			} 
			lastAnimationQueued = curr;
			if (curr.isPendown()) {
				curr.getAnimation().setOnFinished((event) -> {
					animator.endLine();
					animator.bindLineToTurtle(head.getAnimation());
					animationsDonePlaying = true;
				});
			} else {
				curr.getAnimation().setOnFinished((event) -> {
					animator.endLine();
					animationsDonePlaying = true;
				});
			}
			if (animationsDonePlaying) {
				if (head.isPendown()) {
					animator.endLine();
					animator.bindLineToTurtle(head.getAnimation());
				} else {
					animator.endLine();
				}	
				runningAnimation = head;
				animationsDonePlaying = false;
				head.getAnimation().play();
			} 
		}
	}
	
	public void add(TurtleAnimation animation) {
		queue.add(animation);
	}
}
