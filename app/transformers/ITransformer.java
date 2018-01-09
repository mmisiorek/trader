package transformers;

public interface ITransformer<T, U> {
	
	public U transform(T arg) throws TransformNotPossibleException;
	public T reverseTransform(U arg) throws TransformNotPossibleException; 
	
}
