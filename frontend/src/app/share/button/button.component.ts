import { Component, OnInit, Input } from '@angular/core';

@Component({
  selector: 'app-button',
  templateUrl: './button.component.html',
  styleUrls: ['./button.component.css']
})
export class ButtonComponent implements OnInit {

  @Input() public btnName;
  @Input() public btnType = 'button';
  @Input() public btnStyle = 'btn-pink';

  constructor() { }

  ngOnInit() {
  }

}
