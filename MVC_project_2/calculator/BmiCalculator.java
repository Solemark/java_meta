package bmi.calculator;
public class BmiCalculator{
    public static void main(String[] args){
        bmi.model.BmiQueries queries=new bmi.model.BmiQueries();
        bmi.model.IModel model=new bmi.model.BmiModel(queries);
        bmi.presenter.BMIPresenter presenter=new bmi.presenter.BMIPresenter(model);
        bmi.view.IView view=new bmi.view.BMIView(presenter);
        presenter.bind(view);
    }
}