package queue;

public abstract class QueueStateSlots {
	public abstract boolean isEmptyPolymorfic();

	protected abstract Object takePolymorfic();

	protected abstract Object headPolymorfic();
}
