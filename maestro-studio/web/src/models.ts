import React from 'react';

export type HTMLProps<T> = React.DetailedHTMLProps<React.HTMLAttributes<T>, T>
export type DivProps = HTMLProps<HTMLDivElement>

export type UIElementBounds = {
  x: number
  y: number
  width: number
  height: number
}

export type UIElement = {
  id: string
  bounds?: UIElementBounds
  resourceId?: string
  resourceIdIndex?: number
  text?: string
  textIndex?: number
}

export type DeviceScreen = {
  screenshot: string
  width: number
  height: number
  elements: UIElement[]
}