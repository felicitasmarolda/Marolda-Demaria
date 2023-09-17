package queue;

public class EmptySlot extends QueueStateSlots{

	private static final String QueueIsEmpty = "Queue is empty";

	public boolean isEmptyPolymorfic() {
		return true;
	}
	public Object takePolymorfic() {
		throw new Error( QueueIsEmpty );
	}
	
	public Object headPolymorfic() {
		throw new Error( QueueIsEmpty );
	}
}