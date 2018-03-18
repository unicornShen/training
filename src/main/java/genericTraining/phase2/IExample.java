package genericTraining.phase2;

/**
 * <pre>
 * 在實戰中interface的應用是很廣的，
 * 在很多時候，真的沒有他不行.
 * 
 * 其他不需多解釋，看實做...
 * </pre>
 * @author Unicorn
 */
public interface IExample<T, S> {
    
    public T convert(S source);
    
}

