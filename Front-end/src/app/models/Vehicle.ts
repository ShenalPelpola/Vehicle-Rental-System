export class Vehicle{
    private _plateNo : string;
    private _make : string;
    private _model : string;
    private _color : string;
    private _transmissionType : number;
    private _engineCapacity : number;
    private _rentPerDay : number;
    private _imageUrl : string;
;
   

    public get plateNo(){
        return this._plateNo;
    }

    public get make(){
        return this._make;
    }
    public get model(){
        return this._model;
    }

    public get color(){
        return this._color;
    }

    public get transmissionTypeansmo(){
        return this._transmissionType;
    }

    public get engineCapacity(){
        return this._engineCapacity;
    }

    public get rentPerDay(){
        return this._rentPerDay;
    }
    public get imageUrl(){
        return this._imageUrl;
    }

    
}
