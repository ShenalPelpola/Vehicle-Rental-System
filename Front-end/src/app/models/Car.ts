import { Vehicle } from './Vehicle';

export abstract class Car extends Vehicle{
    private _airCondition : boolean;
    private _airBagProtection : boolean;

    public get airCondition(){
        return this._airCondition;
    }

    public get airBagProtection(){
        return this._airBagProtection;
    }
}