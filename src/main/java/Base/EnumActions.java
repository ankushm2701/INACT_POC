package Base;

public class EnumActions {

    public enum ExpectedElementCondition{
        ElementSelectionStateToBe,
        ElementToBeClickable,
        ElementToBeSelected,
        TextToBePresentInElementLocated,
        VisibilityOfElement
    }

    public enum LocatorType{
        Id,
        CssSelector,
        Xpath,
        ClassName
    }
}
