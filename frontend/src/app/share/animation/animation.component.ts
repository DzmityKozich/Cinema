import { Component, OnInit, Input } from '@angular/core';
import {animate, state, style, transition, trigger} from '@angular/animations';

@Component({
  selector: 'app-animation',
  templateUrl: './animation.component.html',
  styleUrls: ['./animation.component.css'],
  animations: [
    trigger('Motion', [
        state('finish', style({color: '#9e174d', transform: 'translateY(10px)'})),
        transition('* => *', [
            style({color: '#2e2e2e', transform: 'translateY(200px)'}),
            animate('1s')
        ])
    ])
]
})
export class AnimationComponent implements OnInit {

  @Input() public animationImage: string;

  constructor() { }

  ngOnInit() {
  }

}
